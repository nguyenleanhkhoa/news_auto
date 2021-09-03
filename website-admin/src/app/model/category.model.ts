export class Category {
  id: number;
  name: string;
  categoryName: string;
  display: number;
  createdAt: number;
  updatedAt: number;

  constructor(id?: number, name?: string, categoryName?: string, display?: number, createdAt?: number, updatedAt?: number) {
    this.id = id;
    this.name = name;
    this.categoryName = categoryName;
    this.display = display;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public fromJson(obj: string): Category {
    this.id = obj['id'];
    this.name = obj['name'];
    this.categoryName = obj['category_name'];
    this.display = obj['display'];
    this.createdAt = obj['created_at'];
    this.updatedAt = obj['update_at'];
    return new Category(this.id, this.name, this.categoryName, this.display, this.createdAt, this.updatedAt);
  }
}
