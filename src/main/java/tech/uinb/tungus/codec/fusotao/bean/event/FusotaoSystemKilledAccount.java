package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import io.emeraldpay.polkaj.types.Address;


//System.KilledAccount(AccountId)
public class FusotaoSystemKilledAccount extends EventRecord {
	private Address param0;

	public Address getParam0(){
		return this.param0;
	}

	public void setParam0(Address param0){
		this.param0 = param0;
	}
}