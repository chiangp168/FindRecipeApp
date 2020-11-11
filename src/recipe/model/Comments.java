package recipe.model;

import java.time.LocalDateTime;

public class Comments {
  private Integer commentId;
  private Integer userId;
  private Integer recipeId;
  private String content;
  private LocalDateTime createdTime;

  public Comments(Integer commentId, Integer userId, Integer recipeId, String content,
      LocalDateTime createdTime) {
    this.commentId = commentId;
    this.userId = userId;
    this.recipeId = recipeId;
    this.content = content;
    this.createdTime = createdTime;
  }

  public Comments(Integer commentId) {
    this.commentId = commentId;
  }

  public Comments(Integer userId, Integer recipeId, String content, LocalDateTime createdTime) {
    this.userId = userId;
    this.recipeId = recipeId;
    this.content = content;
    this.createdTime = createdTime;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(Integer recipeId) {
    this.recipeId = recipeId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }
}
