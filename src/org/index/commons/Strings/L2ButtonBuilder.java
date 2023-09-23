package org.index.commons.Strings;

/**
 * @author Index
 */
public class L2ButtonBuilder
{
    private final static int FONT_COLOR = 0;
    private final static int FONT_SIZE = 1;
    private final static int BUTTON_ACTION = 2;
    private final static int BUTTON_VALUE = 3;
    private final static int BUTTON_HEIGHT = 4;
    private final static int BUTTON_WEIGHT = 5;
    private final static int BUTTON_BACK = 6;
    private final static int BUTTON_FORE = 7;
    private final static int BUTTON_SHOW_TOOLTIP = 8;
    private final static int BUTTON_ITEM_TOOLTIP = 9;
    private final static int BUTTON_ALIGN = 10;
    private final static int BUTTON_ICON = 11;

    private final boolean _textButton;
    private final boolean _minString;
    private final String[] _stringValues = new String[12];

    public L2ButtonBuilder(boolean textButton, boolean minimizeString)
    {
        _textButton = textButton;
        _minString = minimizeString;
    }

    public L2ButtonBuilder addFontColor(String value)
    {
        _stringValues[FONT_COLOR] = value;
        return this;
    }

    public L2ButtonBuilder addFontSize(String value)
    {
        _stringValues[FONT_SIZE] = value;
        return this;
    }

    public L2ButtonBuilder addAction(String action)
    {
        _stringValues[BUTTON_ACTION] = action;
        return this;
    }

    public L2ButtonBuilder addValue(String value)
    {
        _stringValues[BUTTON_VALUE] = value;
        return this;
    }

    public L2ButtonBuilder addHeight(int height)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_HEIGHT] = String.valueOf(height);
        }
        return this;
    }

    public L2ButtonBuilder addWeight(int weight)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_WEIGHT] = String.valueOf(weight);
        }
        return this;
    }

    public L2ButtonBuilder addBack(String texture)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_BACK] = String.valueOf(texture);
        }
        return this;
    }

    public L2ButtonBuilder addFore(String texture)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_FORE] = String.valueOf(texture);
        }
        return this;
    }

    public L2ButtonBuilder addShowTooltip(String value)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_SHOW_TOOLTIP] = String.valueOf(value);
        }
        return this;
    }

    public L2ButtonBuilder addItemTooltip(int itemId)
    {
        if (!_textButton)
        {
            _stringValues[BUTTON_ITEM_TOOLTIP] = String.valueOf(itemId);
        }
        return this;
    }

    public boolean isTextButton()
    {
        return _textButton;
    }

    @Override
    public String toString()
    {
        String rtn = "";
        // <a action="link pccafe_help_lottery001_j.htm"><font color="LEVEL">"Tell me about the daily PC buff."</font></a>
        if (_textButton)
        {
            rtn += "<a ";
            if (_stringValues[BUTTON_ACTION] != null)
            {
                rtn += "action=" + ("\"") + _stringValues[BUTTON_ACTION] + ("\"") + " ";
            }
            rtn += ">";
            if (_stringValues[BUTTON_VALUE] != null && (_stringValues[FONT_COLOR] != null || _stringValues[FONT_SIZE] != null))
            {
                rtn += "<font ";
                if (_stringValues[FONT_SIZE] != null)
                {
                    rtn += "name=" +
                            (_stringValues[FONT_SIZE].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                            _stringValues[FONT_SIZE] +
                            (_stringValues[FONT_SIZE].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                            " ";
                }
                if (_stringValues[FONT_COLOR] != null)
                {
                    rtn += "color=" +
                            (_stringValues[FONT_COLOR].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                            _stringValues[FONT_COLOR] +
                            (_stringValues[FONT_COLOR].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                            " ";
                }
                rtn += ">";
            }
            if (_stringValues[BUTTON_VALUE] != null || _stringValues[BUTTON_ACTION] != null)
            {
                rtn += (_stringValues[BUTTON_ACTION] == null ? " " : _stringValues[BUTTON_VALUE]);
            }
            if (_stringValues[BUTTON_VALUE] != null && (_stringValues[FONT_COLOR] != null || _stringValues[FONT_SIZE] != null))
            {
                rtn += "</font>";
            }
            rtn += "</a>";
        }
        else
        {
            rtn += "<button ";
            if (_stringValues[BUTTON_ALIGN] != null)
            {
                rtn += "align=" + (_minString ? "" : "\"") + _stringValues[BUTTON_ALIGN] + (_minString ? "" : "\"") + " ";
            }
            if (_stringValues[BUTTON_ICON] != null)
            {
                rtn += "icon=" + (_minString ? "" : "\"") + _stringValues[BUTTON_ICON] + (_minString ? "" : "\"") + " ";
            }
            // action="bypass -h value"
            if (_stringValues[BUTTON_ACTION] != null)
            {
                rtn += "action=" + ("\"") + _stringValues[BUTTON_ACTION] + ("\"") + " ";
            }
            // value="<font name="umu umu" color=999999>value</font>"
            if (_stringValues[BUTTON_VALUE] != null || _stringValues[BUTTON_ACTION] != null)
            {
                rtn += "value=" + ("\"");
                if (_stringValues[BUTTON_VALUE] != null && (_stringValues[FONT_COLOR] != null || _stringValues[FONT_SIZE] != null))
                {
                    rtn += "<font ";
                    if (_stringValues[FONT_SIZE] != null)
                    {
                        rtn += "name=" +
                                (_stringValues[FONT_SIZE].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                                _stringValues[FONT_SIZE] +
                                (_stringValues[FONT_SIZE].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                                " ";
                    }
                    if (_stringValues[FONT_COLOR] != null)
                    {
                        rtn += "color=" +
                                (_stringValues[FONT_COLOR].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                                _stringValues[FONT_COLOR] +
                                (_stringValues[FONT_COLOR].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                                " ";
                    }
                    rtn += ">";
                }
                rtn += (_stringValues[BUTTON_ACTION] == null ? " " : _stringValues[BUTTON_VALUE]);
                if (_stringValues[BUTTON_VALUE] != null && (_stringValues[FONT_COLOR] != null || _stringValues[FONT_SIZE] != null))
                {
                    rtn += "</font>";
                }
                rtn += ("\"") + " ";
            }
            if (_stringValues[BUTTON_HEIGHT] != null)
            {
                rtn += "height=" + (_minString ? "" : "\"") + _stringValues[BUTTON_HEIGHT] + (_minString ? "" : "\"") + " ";
            }
            if (_stringValues[BUTTON_WEIGHT] != null)
            {
                rtn += "weight=" + (_minString ? "" : "\"") + _stringValues[BUTTON_WEIGHT] + (_minString ? "" : "\"") + " ";
            }
            if (_stringValues[BUTTON_SHOW_TOOLTIP] != null && _stringValues[BUTTON_ITEM_TOOLTIP] == null)
            {
                rtn += "tooltip=" +
                        (_stringValues[BUTTON_SHOW_TOOLTIP].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                        _stringValues[BUTTON_SHOW_TOOLTIP] +
                        (_stringValues[BUTTON_SHOW_TOOLTIP].contains(" ") ? ("\"") : (_minString ? "" : "\"")) +
                        " ";
            }
            if (_stringValues[BUTTON_ITEM_TOOLTIP] != null)
            {
                rtn += "itemtooltip=" + (_minString ? "" : "\"") + _stringValues[BUTTON_ITEM_TOOLTIP] + (_minString ? "" : "\"") + " ";
            }
            rtn += ">" + (_minString ? "" : "</button>");
        }
        return rtn;
    }
}
