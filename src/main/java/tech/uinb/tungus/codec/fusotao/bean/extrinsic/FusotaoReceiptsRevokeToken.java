package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;


//Receipts.RevokeToken(token:T::TokenId,dominator:<T::Lookup as StaticLookup>::Source)
public class FusotaoReceiptsRevokeToken extends ExtrinsicCall {
	private Hash256 Token;
	private Address Dominator;

	public Hash256 getToken(){
		return this.Token;
	}

	public void setToken(Hash256 Token){
		this.Token = Token;
	}

	public Address getDominator(){
		return this.Dominator;
	}

	public void setDominator(Address Dominator){
		this.Dominator = Dominator;
	}
}