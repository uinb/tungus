package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import java.math.BigInteger;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.Grant(dominator:<T::Lookup as StaticLookup>::Source,amount:Compact<BalanceOf<T>>,memo:UID)
public class FusotaoReceiptsGrant extends ExtrinsicCall {
	private Address Dominator;
	private DotAmount Amount;
	private BigInteger Memo;

	public Address getDominator(){
		return this.Dominator;
	}

	public void setDominator(Address Dominator){
		this.Dominator = Dominator;
	}

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}

	public BigInteger getMemo(){
		return this.Memo;
	}

	public void setMemo(BigInteger Memo){
		this.Memo = Memo;
	}
}