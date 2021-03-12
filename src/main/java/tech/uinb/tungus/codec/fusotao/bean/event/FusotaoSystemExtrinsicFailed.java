package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.fusotao.type.DispatchError;
import tech.uinb.tungus.codec.fusotao.type.DispatchInfo;

//System.ExtrinsicFailed(DispatchError,DispatchInfo)
public class FusotaoSystemExtrinsicFailed extends EventRecord {
	private DispatchError param0;
	private DispatchInfo param1;

	public DispatchError getParam0(){
		return this.param0;
	}

	public void setParam0(DispatchError param0){
		this.param0 = param0;
	}

	public DispatchInfo getParam1(){
		return this.param1;
	}

	public void setParam1(DispatchInfo param1){
		this.param1 = param1;
	}
}