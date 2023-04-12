package com.ecom_fin.customer.servicesImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ecom_fin.customer.execptions.UserException;
import com.ecom_fin.customer.external.services.AccountHolderService;
import com.ecom_fin.customer.external.services.CartService;
import com.ecom_fin.customer.models.Cart;
import com.ecom_fin.customer.models.Users;
import com.ecom_fin.customer.repositories.UserRepository;
import com.ecom_fin.customer.sevices.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountHolderService accountService;

    
    @Autowired
    private RestTemplate restTemplate;
    
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Users saveUser(Users user)  {
        System.out.println("User Save Repo method");
        String randomUID = UUID.randomUUID().toString();
        user.setUserId(randomUID);
        Users user1 =  userRepo.save(user);
        Cart cart = new Cart();
        cart.setUserId(randomUID);
        Cart added_cart = cartService.addCart(cart);
        user.setCart(added_cart);
        accountService.creaAccountHolder(user.getAccountholder(), randomUID);
        return user1;
    }

    @Override
    public List<Users> getAllUsers() {

        List<Cart> carts = cartService.getAllCarts();
        List<Users> users = userRepo.findAll();

        Map<String, Cart> cartMap = carts.stream().collect(Collectors.toMap(Cart::getUserId, Function.identity()));
        return users.stream().map(user -> {
                    Cart cart = cartMap.get(user.getUserId());
                    if (cart != null) {
                        user.setCart(cart);
                    }
                    return user;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Users getUser(String userId) {
        // return userRepo.findById(userId);
        Users user = userRepo.findById(userId).orElseThrow( () -> new UserException("User not found with Id : "+userId) );
        
        // using REST TEMPLATE

        // try{
        // Cart forObject = restTemplate.getForObject("http://CART_SERVICE/carts/"+userId, Cart.class);
        // logger.info("{}",forObject);
        // if(forObject!=null){
        //     user.setCart(forObject);
        // }
        // }catch(Exception e){
        //         System.out.println("Not added any products to cart...");
        // }
        

        // USING FEIGN CLIENT
        
        try{
        Cart cart =  cartService.getCartByUserId(userId);
        user.setCart(cart);
        }catch(Exception e){
            System.out.println("Not added any products to cart...");
        }

        return user;
    }

    @Override
    public Users getUserByAccount(String userId) {
        return userRepo.findById(userId).orElseThrow( () -> new UserException("User not found with Id : "+userId) );
    }
    
}
