package org.index.enums.otp;

public enum SHACryptType
{
    SHA001("HmacSHA1", "SHA1"),
    SHA256("HmacSHA256", "SHA256"),
    SHA512("HmacSHA512", "SHA512"),
    ;

    private final String _hmac;
    private final String _name;

    SHACryptType(String hmac, String defaultName)
    {
        _hmac = hmac;
        _name = defaultName;
    }

    public String getHmac()
    {
        return _hmac;
    }

    public String getName()
    {
        return _name;
    }
}
