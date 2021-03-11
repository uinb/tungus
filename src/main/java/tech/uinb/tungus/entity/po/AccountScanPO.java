package tech.uinb.tungus.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("page requst params")
@Data
@Accessors(chain = true)
public class AccountScanPO extends PagePO{

  @ApiModelProperty("account hash")
  private String account;

  public boolean check(){
    if (getSize() == null || getPage() == null || account == null){
        return false ;
    }return true ;
  }

}
