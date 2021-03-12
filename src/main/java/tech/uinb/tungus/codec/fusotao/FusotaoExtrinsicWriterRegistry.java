package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.WriterRegistry;

public class FusotaoExtrinsicWriterRegistry implements WriterRegistry {
	private HashMap<Class, ScaleWriter> map = new HashMap<>();
	public FusotaoExtrinsicWriterRegistry() {
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenIssue.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoTokenIssueWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesTransferKeepAlive.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoBalancesTransferKeepAliveWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevoke.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsRevokeWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToDeductStashToken.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsMergeToDeductStashTokenWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToAddStash.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsMergeToAddStashWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenTransfer.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoTokenTransferWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToDeductStash.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsMergeToDeductStashWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsSlash.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsSlashWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsQuitDominator.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsQuitDominatorWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToAddStashToken.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsMergeToAddStashTokenWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmWithdraw.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsConfirmWithdrawWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTimestampSet.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoTimestampSetWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesTransfer.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoBalancesTransferWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrant.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsGrantWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesForceTransfer.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoBalancesForceTransferWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevokeToken.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsRevokeTokenWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmTokenWithdraw.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsConfirmTokenWithdrawWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrantToken.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsGrantTokenWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesSetBalance.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoBalancesSetBalanceWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimTokenDominator.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsClaimTokenDominatorWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimDominator.class, new tech.uinb.tungus.codec.fusotao.writer.extrinsic.FusotaoReceiptsClaimDominatorWriter());
	}

	public ScaleWriter getWriter(Class clazz) {
		return map.get(clazz);
	}
}
