package tech.uinb.tungus.service;

import java.util.List;

public interface AccountTransactionService {

  void save(long accountId, long extId);

  List<Long> getTransferIdsByAccount(long accountId);
}
