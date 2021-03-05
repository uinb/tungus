package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.ReaderRegistry;
import tech.uinb.tungus.codec.fusotao.reader.extrinsic.*;

public class FusotaoExtrinsicReaderRegistry implements ReaderRegistry {
	private HashMap<String, ScaleReader> map = new HashMap<>();
	public FusotaoExtrinsicReaderRegistry() {
		map.put("Token.transfer", new FusotaoTokenTransferReader());
		map.put("Balances.transfer", new FusotaoBalancesTransferReader());
		map.put("Receipts.grant_token", new FusotaoReceiptsGrantTokenReader());
		map.put("Balances.transfer_keep_alive", new FusotaoBalancesTransferKeepAliveReader());
		map.put("Receipts.grant", new FusotaoReceiptsGrantReader());
		map.put("Receipts.revoke", new FusotaoReceiptsRevokeReader());
		map.put("Receipts.confirm_withdraw", new FusotaoReceiptsConfirmWithdrawReader());
		map.put("Balances.force_transfer", new FusotaoBalancesForceTransferReader());
		map.put("Receipts.merge_to_add_stash_token", new FusotaoReceiptsMergeToAddStashTokenReader());
		map.put("Receipts.confirm_token_withdraw", new FusotaoReceiptsConfirmTokenWithdrawReader());
		map.put("Receipts.claim_dominator", new FusotaoReceiptsClaimDominatorReader());
		map.put("Receipts.revoke_token", new FusotaoReceiptsRevokeTokenReader());
		map.put("Timestamp.set", new FusotaoTimestampSetReader());
		map.put("Receipts.merge_to_deduct_stash_token", new FusotaoReceiptsMergeToDeductStashTokenReader());
		map.put("Receipts.merge_to_add_stash", new FusotaoReceiptsMergeToAddStashReader());
		map.put("Balances.set_balance", new FusotaoBalancesSetBalanceReader());
		map.put("Receipts.claim_token_dominator", new FusotaoReceiptsClaimTokenDominatorReader());
		map.put("Receipts.merge_to_deduct_stash", new FusotaoReceiptsMergeToDeductStashReader());
		map.put("Token.issue", new FusotaoTokenIssueReader());
		map.put("Receipts.quit_dominator", new FusotaoReceiptsQuitDominatorReader());
		map.put("Receipts.slash", new FusotaoReceiptsSlashReader());
	}

	public ScaleReader getReader(String name) {
		return map.get(name);
	}
}
