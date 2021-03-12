package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.ReaderRegistry;

public class FusotaoExtrinsicReaderRegistry implements ReaderRegistry {
	private HashMap<String, ScaleReader> map = new HashMap<>();
	public FusotaoExtrinsicReaderRegistry() {
		map.put("Token.transfer", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoTokenTransferReader());
		map.put("Balances.transfer", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoBalancesTransferReader());
		map.put("Receipts.grant_token", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsGrantTokenReader());
		map.put("Balances.transfer_keep_alive", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoBalancesTransferKeepAliveReader());
		map.put("Receipts.grant", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsGrantReader());
		map.put("Receipts.revoke", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsRevokeReader());
		map.put("Receipts.confirm_withdraw", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsConfirmWithdrawReader());
		map.put("Balances.force_transfer", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoBalancesForceTransferReader());
		map.put("Receipts.merge_to_add_stash_token", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsMergeToAddStashTokenReader());
		map.put("Receipts.confirm_token_withdraw", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsConfirmTokenWithdrawReader());
		map.put("Receipts.claim_dominator", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsClaimDominatorReader());
		map.put("Receipts.revoke_token", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsRevokeTokenReader());
		map.put("Timestamp.set", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoTimestampSetReader());
		map.put("Receipts.merge_to_deduct_stash_token", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsMergeToDeductStashTokenReader());
		map.put("Receipts.merge_to_add_stash", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsMergeToAddStashReader());
		map.put("Balances.set_balance", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoBalancesSetBalanceReader());
		map.put("Receipts.claim_token_dominator", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsClaimTokenDominatorReader());
		map.put("Receipts.merge_to_deduct_stash", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsMergeToDeductStashReader());
		map.put("Token.issue", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoTokenIssueReader());
		map.put("Receipts.quit_dominator", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsQuitDominatorReader());
		map.put("Receipts.slash", new tech.uinb.tungus.codec.fusotao.reader.extrinsic.FusotaoReceiptsSlashReader());
	}

	public ScaleReader getReader(String name) {
		return map.get(name);
	}
}
