/* Decompiler 21ms, total 1417ms, lines 64 */
package com.is.sets;

import java.io.Serializable;

public class Sets implements Serializable {
   static final long serialVersionUID = 36856312222151L;
   private String branch;
   private String id;
   private String value;
   private String name;
   private Long editable;

   public Sets() {
   }

   public Sets(String branch, String id, String value, String name, Long editable) {
      this.branch = branch;
      this.id = id;
      this.value = value;
      this.name = name;
      this.editable = editable;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getEditable() {
      return this.editable;
   }

   public void setEditable(Long editable) {
      this.editable = editable;
   }
}