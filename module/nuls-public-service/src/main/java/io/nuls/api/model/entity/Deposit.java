/*
 * *
 *  * MIT License
 *  *
 *  * Copyright (c) 2017-2019 nuls.io
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software , to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */
package io.nuls.api.model.entity;


import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.basic.NulsOutputStreamBuffer;
import io.nuls.base.data.Address;
import io.nuls.base.data.BaseNulsData;
import io.nuls.base.data.NulsHash;
import io.nuls.core.exception.NulsException;
import io.nuls.core.parse.SerializeUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;

/**
 * 委托信息类
 * Delegated information class
 *
 * @author tag
 * 2018/11/28
 */
public class Deposit extends BaseNulsData {

    /**
     * 委托金额
     * */
    private BigInteger deposit;
    /**
     * 委托账户
     * */
    private byte[] address;
    /**
     * 资产链ID
     * */
    private int assetChainId;
    /**
     * 资产ID
     * */
    private int assetId;
    /**
     * 委托类型
     * */
    private byte depositType;
    /**
     * 委托时长
     * */
    private byte timeType;
    /**
     * 委托时间
     * */
    private transient long time;
    /**
     * 委托交易HASH
     * */
    private transient NulsHash txHash;
    /**
     * 委托交易被打包的高度
     * */
    private transient long blockHeight = -1L;
    /**
     * 退出委托高度
     * */
    private transient long delHeight = -1L;


    /**
     * serialize important field
     */
    @Override
    protected void serializeToStream(NulsOutputStreamBuffer stream) throws IOException {
        stream.writeBigInteger(deposit);
        stream.write(address);
        stream.writeUint16(assetChainId);
        stream.writeUint16(assetId);
        stream.writeByte(depositType);
        stream.writeByte(timeType);
    }

    @Override
    public void parse(NulsByteBuffer byteBuffer) throws NulsException {
        this.deposit = byteBuffer.readBigInteger();
        this.address = byteBuffer.readBytes(Address.ADDRESS_LENGTH);
        this.assetChainId = byteBuffer.readUint16();
        this.assetId = byteBuffer.readUint16();
        this.depositType = byteBuffer.readByte();
        this.timeType = byteBuffer.readByte();
    }

    @Override
    public int size() {
        int size = 0;
        size += SerializeUtils.sizeOfBigInteger();
        size += Address.ADDRESS_LENGTH;
        size += SerializeUtils.sizeOfUint16() * 2;
        size += 2;
        return size;
    }


    public BigInteger getDeposit() {
        return deposit;
    }

    public void setDeposit(BigInteger deposit) {
        this.deposit = deposit;
    }

    public byte[] getAddress() {
        return address;
    }

    public void setAddress(byte[] address) {
        this.address = address;
    }

    public int getAssetChainId() {
        return assetChainId;
    }

    public void setAssetChainId(int assetChainId) {
        this.assetChainId = assetChainId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public byte getDepositType() {
        return depositType;
    }

    public void setDepositType(byte depositType) {
        this.depositType = depositType;
    }

    public byte getTimeType() {
        return timeType;
    }

    public void setTimeType(byte timeType) {
        this.timeType = timeType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public NulsHash getTxHash() {
        return txHash;
    }

    public void setTxHash(NulsHash txHash) {
        this.txHash = txHash;
    }

    public long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Set<byte[]> getAddresses() {
        return Set.of(this.address);
    }

    @Override
    public Deposit clone() throws CloneNotSupportedException {
        return (Deposit) super.clone();
    }

}
