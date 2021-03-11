package tech.uinb.tungus.service;

import java.util.List;

public interface AccountExtrinsicService {

  void save(long accountId, long extId);

  List<Long> getExtIdsByAccount(long accountId);
}
