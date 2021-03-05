package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.WriterRegistry;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.*;
import tech.uinb.tungus.codec.fusotao.writer.extrinsic.*;

public class FusotaoExtrinsicWriterRegistry implements WriterRegistry {
	private HashMap<Class, ScaleWriter> map = new HashMap<>();
	public FusotaoExtrinsicWriterRegistry() {
		map.put(FusotaoTokenTransfer.class, new FusotaoTokenTransferWriter());
		map.put(FusotaoReceiptsRevoke.class, new FusotaoReceiptsRevokeWriter());
		map.put(FusotaoBalancesTransfer.class, new FusotaoBalancesTransferWriter());
		map.put(FusotaoTokenIssue.class, new FusotaoTokenIssueWriter());
		map.put(FusotaoReceiptsGrantToken.class, new FusotaoReceiptsGrantTokenWriter());
		map.put(FusotaoReceiptsMergeToDeductStashToken.class, new FusotaoReceiptsMergeToDeductStashTokenWriter());
		map.put(FusotaoReceiptsMergeToAddStash.class, new FusotaoReceiptsMergeToAddStashWriter());
		map.put(FusotaoTimestampSet.class, new FusotaoTimestampSetWriter());
		map.put(FusotaoReceiptsSlash.class, new FusotaoReceiptsSlashWriter());
		map.put(FusotaoBalancesForceTransfer.class, new FusotaoBalancesForceTransferWriter());
		map.put(FusotaoReceiptsConfirmWithdraw.class, new FusotaoReceiptsConfirmWithdrawWriter());
		map.put(FusotaoReceiptsGrant.class, new FusotaoReceiptsGrantWriter());
		map.put(FusotaoReceiptsClaimDominator.class, new FusotaoReceiptsClaimDominatorWriter());
		map.put(FusotaoReceiptsQuitDominator.class, new FusotaoReceiptsQuitDominatorWriter());
		map.put(FusotaoBalancesTransferKeepAlive.class, new FusotaoBalancesTransferKeepAliveWriter());
		map.put(FusotaoReceiptsMergeToAddStashToken.class, new FusotaoReceiptsMergeToAddStashTokenWriter());
		map.put(FusotaoReceiptsMergeToDeductStash.class, new FusotaoReceiptsMergeToDeductStashWriter());
		map.put(FusotaoReceiptsConfirmTokenWithdraw.class, new FusotaoReceiptsConfirmTokenWithdrawWriter());
		map.put(FusotaoReceiptsClaimTokenDominator.class, new FusotaoReceiptsClaimTokenDominatorWriter());
		map.put(FusotaoReceiptsRevokeToken.class, new FusotaoReceiptsRevokeTokenWriter());
		map.put(FusotaoBalancesSetBalance.class, new FusotaoBalancesSetBalanceWriter());
	}

	public ScaleWriter getWriter(Class clazz) {
		return map.get(clazz);
	}
}
