package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import java.math.BigInteger;


//Timestamp.Set(now:Compact<T::Moment>)
public class FusotaoTimestampSet extends ExtrinsicCall {
	private BigInteger Now;

	public BigInteger getNow(){
		return this.Now;
	}

	public void setNow(BigInteger Now){
		this.Now = Now;
	}
}