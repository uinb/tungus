package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Balances.TransferKeepAlive(dest:<T::Lookup as StaticLookup>::Source,value:Compact<T::Balance>)
public class FusotaoBalancesTransferKeepAlive extends ExtrinsicCall {
	private Address Dest;
	private DotAmount Value;

	public Address getDest(){
		return this.Dest;
	}

	public void setDest(Address Dest){
		this.Dest = Dest;
	}

	public DotAmount getValue(){
		return this.Value;
	}

	public void setValue(DotAmount Value){
		this.Value = Value;
	}
}