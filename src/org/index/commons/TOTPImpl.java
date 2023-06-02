package org.index.commons;

import org.index.crypt.Base32;
import org.index.crypt.Base64;
import org.index.enums.otp.BaseCryptType;
import org.index.enums.otp.SHACryptType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author Index
 */
public class TOTPImpl
{
    public static final String TEST_SITE = "https://totp.danhersam.com/";
    public static final String TEST_KEY = "KEUPRXB4NNE3WWEP5MAMNGP5SFMT5SL3";

    private BaseCryptType _baseType;
    private SHACryptType _SHAType;
    private int _keyLength;

    public TOTPImpl()
    {
        _baseType = BaseCryptType.BASE32;
        _SHAType = SHACryptType.SHA001;
        _keyLength = 20;
    }

    public void setBaseCryptType(BaseCryptType type)
    {
        _baseType = type;
    }

    public void setSHACryptType(SHACryptType type)
    {
        _SHAType = type;
    }

    public void setSecurityKeyLength(int size)
    {
        _keyLength = size;
    }

    /**
     *
     * @param securityKey secret sequence for user
     * @param requestedDigit count of digits
     * @return key which correct in next time period
     */
    public int generateKey(String securityKey, int requestedDigit)
    {
        return generateKey(securityKey, requestedDigit, getPeriod());
    }

    private int generateKey(String securityKey, int requestedDigit, long timePeriod)
    {
        final byte[] decodedSecurityKey = decodeSecurityCode(securityKey);
        byte[] macArray = encodeHmacSHA(decodedSecurityKey, timePeriod);
        try
        {
            final int calculatedOffset = calculateOffset(macArray);
            return getKeyFromSequence(macArray, calculatedOffset, requestedDigit);
        }
        catch (NullPointerException npe)
        {
            npe.printStackTrace();
            return 0;
        }
    }

    /**
     * Some applications send "outdate" keys on one "timeperiod" back
     * As exemple:
     * * 00:00 - Test Page will show AAAAAA - GA will show ZZZZZZ
     * * 00:30 - Test Page will show BBBBBB - GA will show AAAAAA
     * @param otpKey key for verification
     * @param securityKey secret sequence for user
     * @param requestedDigit count of digits
     * @return true - all fine, false - key is incorrect
     */
    public boolean verifyKey(int otpKey, String securityKey, int requestedDigit)
    {
        int windowSize = 2;
        final long timeWindow = getPeriod();
        for (int i = -1; i <= windowSize; i++)
        {
            final int requestedKey = generateKey(securityKey, requestedDigit, (timeWindow + i));

            if (requestedKey == otpKey)
            {
                return true;
            }
        }
        return false;
    }

    private String generateSecurityCode()
    {
        final int length = _baseType == BaseCryptType.BASE32 ? Base32.getByteLengthForBaseCrypt(_keyLength) : Base64.getByteLengthForBaseCrypt(_keyLength);
        // generating security key
        final byte[] securityBytes = Rnd.getSecureBytes(length);
        return _baseType == BaseCryptType.BASE32 ? Base32.encode(securityBytes) : Base64.encode(securityBytes);
    }

    private byte[] decodeSecurityCode(String securityKey)
    {
        return _baseType == BaseCryptType.BASE32 ? Base32.decode(securityKey) : Base64.decode(securityKey);
    }

    private long getPeriod()
    {
        // current time - synchronize time / time of life for code
        return (System.currentTimeMillis() - 0) / TimeUnit.SECONDS.toMillis(30);
    }

    private byte[] encodeHmacSHA(byte[] decodedSecurityKey, long timePeriod)
    {
        try
        {
            SecretKeySpec signKey = new SecretKeySpec(decodedSecurityKey, _SHAType.getHmac());
            Mac mac = Mac.getInstance(_SHAType.getHmac());
            mac.init(signKey);
            return mac.doFinal(ByteBuffer.allocate(Long.BYTES).putLong(timePeriod).array());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private int calculateOffset(byte[] sequence)
    {
        return sequence[sequence.length - 1] & 0xF;
    }

    private int getKeyFromSequence(byte[] sequence, int offset, int requestedDigit)
    {
        long truncate = 0;
        for (int index = 0; index < 4; index++)
        {
            truncate <<= 8;
            truncate |= (sequence[offset + index] & 0xFF);
        }
        truncate &= 0x7FFFFFFF;
        truncate %= (int) Math.pow(10, requestedDigit);
        return (int) (truncate);
    }

    public static TOTPImpl getInstance()
    {
        return Singleton.INSTANCE;
    }

    private static class Singleton
    {
        private static final TOTPImpl INSTANCE = new TOTPImpl();
    }
}
