package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//购物车类
public class Cart {

    //购买商品的数量
    private HashMap<Items,Integer> goods;
    //总金额
    private double totalPrice;


    public Cart()
    {
        goods = new HashMap<Items,Integer>();
        totalPrice = 0.0;
    }

    public HashMap<Items, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Items, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    //添加商品
    public boolean addGoodsToCart(Items item,int number)
    {
        if (goods.containsKey(item))
        {
            goods.put(item,number+goods.get(item));
        }
        else
        {
            goods.put(item,number);
        }
        calcTotalPrice();
        return true;
    }
    //删除商品
    public boolean deleteGoodsFromCart(Items item)
    {
        goods.remove(item);
        calcTotalPrice();
        return true;
    }
    //统计购物车总金额
    public double calcTotalPrice()
    {
        double sum = 0.0;
        Set<Items> keys = goods.keySet();
        Iterator<Items> itor = keys.iterator();
        while (itor.hasNext())
        {
            Items item = itor.next();
            sum += item.getPrice() * goods.get(item);
        }
        this.setTotalPrice(sum);

        return this.getTotalPrice();
    }


    public static void main(String[] args)
    {
        Items i1 = new Items(1,"nike","浙江",300,500,"001.jpg");
        Items i2 = new Items(2,"anta","广州",200,500,"002.jpg");
        Items i3 = new Items(1,"nike","浙江",300,500,"001.jpg");

        Cart cart = new Cart();
        cart.addGoodsToCart(i1,1);
        cart.addGoodsToCart(i2,2);
        cart.addGoodsToCart(i1,3);

        //遍历商品集合
        Set<Map.Entry<Items,Integer>> items = cart.getGoods().entrySet();
        for(Map.Entry<Items,Integer> obj : items)
        {
            System.out.println(obj);
        }

        System.out.println("总金额："+cart.getTotalPrice());

    }
}
