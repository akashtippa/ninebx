package com.ninebx.ui.auth.passwordHash;

import org.spongycastle.crypto.CipherParameters;

/**
 * Created by Alok on 15/01/18.
 */

public class CustomKeyParameter
        implements CipherParameters
{
    private byte[]  key;

    public CustomKeyParameter(
            byte[]  key)
    {
        this(key, 0, key.length);
    }

    public CustomKeyParameter(
            byte[]  key,
            int     keyOff,
            int     keyLen)
    {
        this.key = new byte[keyLen];

        System.arraycopy(key, keyOff, this.key, 0, keyLen);
    }

    public byte[] getKey()
    {
        return key;
    }
}
