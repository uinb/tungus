package tech.uinb.tungus.codec.fusotao.type;

import io.emeraldpay.polkaj.types.Address;

import java.math.BigInteger;

public class Authority {
    public Address authorityId;
    public BigInteger authorityWeight; //U64

    public Address getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Address authorityId) {
        this.authorityId = authorityId;
    }

    public BigInteger getAuthorityWeight() {
        return authorityWeight;
    }

    public void setAuthorityWeight(BigInteger authorityWeight) {
        this.authorityWeight = authorityWeight;
    }
}
