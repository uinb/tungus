package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import io.emeraldpay.polkaj.types.Address;


//Receipts.AssetsRevokeSubmitted(AccountId,AccountId)
public class FusotaoReceiptsAssetsRevokeSubmitted extends EventRecord {
	private Address param0;
	private Address param1;

	public Address getParam0(){
		return this.param0;
	}

	public void setParam0(Address param0){
		this.param0 = param0;
	}

	public Address getParam1(){
		return this.param1;
	}

	public void setParam1(Address param1){
		this.param1 = param1;
	}
}