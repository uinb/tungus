package tech.uinb.tungus.entity.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageDataVO<T> {

  private int index;
  private long pageCount;
  private int size;
  private long total;
  private List<T> list = new ArrayList();

  public static PageDataVO valueOf(List list,int index,int size,long total){
    return new PageDataVO()
        .setList(list)
        .setIndex(index)
        .setSize(size)
        .setTotal(total)
        .setPageCount(total % size == 0 ? total / index : total / size + 1);
  }

}
