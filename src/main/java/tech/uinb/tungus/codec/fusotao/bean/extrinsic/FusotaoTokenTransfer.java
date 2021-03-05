package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Token.Transfer(token:<T as Trait>::TokenId,target:<T::Lookup as StaticLookup>::Source,amount:Compact<T::Balance>)
public class FusotaoTokenTransfer extends ExtrinsicCall {
	private Hash256 Token;
	private Address Target;
	private DotAmount Amount;

	public Hash256 getToken(){
		return this.Token;
	}

	public void setToken(Hash256 Token){
		this.Token = Token;
	}

	public Address getTarget(){
		return this.Target;
	}

	public void setTarget(Address Target){
		this.Target = Target;
	}

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}
}