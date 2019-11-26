package servlet;

import dao.ItemsDao;
import entity.Cart;
import entity.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {

    private String action;
    private ItemsDao itemsDao = new ItemsDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter outer = response.getWriter();
        if(request.getParameter("action")!=null)
        {
            this.action = request.getParameter("action");
            if (action.equals("add"))
            {
                if (addToaCart(request,response))
                {
                    request.getRequestDispatcher("/success.jsp").forward(request,response);
                }
                else
                {
                    request.getRequestDispatcher("/failure.jsp").forward(request,response);
                }

            }
            if (action.equals("show"))
            {
                if (showFromCart(request,response))
                {
                    request.getRequestDispatcher("/cart.jsp").forward(request,response);
                }
            }
            if (action.equals("delete"))
            {
                if (deleteFromCart(request,response))
                {
                    request.getRequestDispatcher("/cart.jsp").forward(request,response);
                }
                else
                {
                    request.getRequestDispatcher("/cart.jsp").forward(request,response);
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private boolean addToaCart(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("id");
        String number = request.getParameter("num");
        Items item = itemsDao.getItemsById(Integer.parseInt(id));
        //是否第一次添加商品到购物车，需要给session中创建一个新的购物车对象
        if (request.getSession().getAttribute("cart") == null)
        {
            Cart cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart.addGoodsToCart(item,Integer.parseInt(number)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //查看购物车中商品
    private boolean showFromCart(HttpServletRequest request, HttpServletResponse response)
    {
        return true;
    }

    //查看购物车中商品
    private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("id");
        Items item = itemsDao.getItemsById(Integer.parseInt(id));
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart.deleteGoodsFromCart(item))
        {
            return true;
        }
        else
        {
            return false;
        }


    }

}
