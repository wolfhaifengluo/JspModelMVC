package entity;

//商品类
public class Items {

    private int id; // 商品编号
    private String name; // 商品名称
    private String city; // 产地
    private int price; // 价格
    private int number; // 库存
    private String picture; // 商品图片

    public Items(){}

    public Items(int id,String name,String city,int price,int number,String picture)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.picture = picture;
        this.price = price;
        this.number = number;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", picture='" + picture + '\'' +
                '}';
    }
    //重写一下两个方法，使得同类商品合并
    @Override
    public int hashCode() {
        return this.getId() + this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
        {
            return true;
        }
        if(obj instanceof Items)
        {
            Items i = (Items) obj;
            if (this.getId() ==i.getId() && this.getName().equals(i.getName()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            {
                return false;
            }
    }
}
