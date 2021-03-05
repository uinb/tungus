package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Address;


//Receipts.Slash(dominator:<T::Lookup as StaticLookup>::Source)
public class FusotaoReceiptsSlash extends ExtrinsicCall {
	private Address Dominator;

	public Address getDominator(){
		return this.Dominator;
	}

	public void setDominator(Address Dominator){
		this.Dominator = Dominator;
	}
}