export class WebSource {
  id: number;
  url: string;
  name: string;
  status: number;
  createdAt: number;
  updatedAt: number;

  constructor(id?: number, url?: string, name?: string, status?: number, createdAt?: number, updatedAt?: number) {
    this.id = id;
    this.url = url;
    this.name = name;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public fromJson(obj: string): WebSource {
    this.id = obj['id'];
    this.name = obj['name'];
    this.url = obj['url'];
    this.status = obj['status'];
    this.createdAt = obj['created_at'];
    this.updatedAt = obj['update_at'];
    return new WebSource(this.id, this.name, this.url, this.status, this.createdAt, this.updatedAt);
  }
}
