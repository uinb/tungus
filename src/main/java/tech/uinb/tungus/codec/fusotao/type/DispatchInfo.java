package tech.uinb.tungus.codec.fusotao.type;

import io.emeraldpay.polkaj.json.RuntimeDispatchInfoJson.DispatchClass;

import java.math.BigInteger;

public class DispatchInfo {
    private BigInteger weight;
    private DispatchClass cls;
    private Boolean paysFee;

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public DispatchClass getCls() {
        return cls;
    }

    public void setCls(DispatchClass cls) {
        this.cls = cls;
    }

    public Boolean getPaysFee() {
        return paysFee;
    }

    public void setPaysFee(Boolean paysFee) {
        this.paysFee = paysFee;
    }
}
