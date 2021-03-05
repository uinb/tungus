package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.fusotao.type.DispatchInfo;


//System.ExtrinsicSuccess(DispatchInfo)
public class FusotaoSystemExtrinsicSuccess extends EventRecord {
	private DispatchInfo param0;

	public DispatchInfo getParam0(){
		return this.param0;
	}

	public void setParam0(DispatchInfo param0){
		this.param0 = param0;
	}
}