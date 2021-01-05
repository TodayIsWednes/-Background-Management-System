package com.codewinter.backgroundmanagement.vo;

import com.codewinter.backgroundmanagement.entity.Perm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermVo {
   private Long id;
   private String permName;
   private Long parentId;
   private String label;
   private int level;
   private String url;
   private Character visible;
   private List<PermVo>  children;

   public void setPropertyFromPerm(Perm perm){
      this.id=perm.getId();
      this.permName=perm.getPermName();
      this.parentId=perm.getParentModule();
      this.label=perm.getPermName();
      this.level=perm.getLevel();
      this.url=perm.getUrl();
      this.visible=perm.getVisible();
      this.children=new ArrayList<>();
   }
}
