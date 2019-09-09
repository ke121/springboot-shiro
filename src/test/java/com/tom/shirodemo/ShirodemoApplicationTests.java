package com.tom.shirodemo;

import com.tom.shirodemo.shiro.bean.Menu;
import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.tree.service.TreeService;
import com.tom.shirodemo.utils.ShiroKit;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirodemoApplicationTests {

    @Test
    public void contextLoads() {
        String salt = ShiroKit.getRandomSalt( "tom") ;
        System.out.println("盐值======" + salt );
        String password = ShiroKit.md5("123456",salt ) ;
        System.out.println("密码======" + password);
        System.out.println("============" + ByteSource.Util.bytes("admin3b41d727b0af4164a9454f6805ac36b9")) ;
    }

    @Test
    public void test(){
//        BigDecimal realcom = new BigDecimal(0.01) ;
//        System.out.println(realc);
//        //===============
//        if (refundVOList != null && refundVOList.size() > 0) {
//            PayOrderRefundVO payOrderRefundVO = refundVOList.get(0);
//            refundOrderId = payOrderRefundVO.getId();
//            acctype = "收款账号";
//            refundDesc = "已全额退款";
//            for (PmsMerchantOperatorVO operatorVO : merchantOperatorVOList) {
//                if (payOrderRefundVO.getRefundLoginId() != null && payOrderRefundVO.getRefundLoginId().equals(operatorVO.getId())) {
//                    if (MerchantRole.ROLE_1.getRoleNum().equals(operatorVO.getRoleValue())) {// 收银员显示用户名
//                        username = "主账号";
//                    }else{
//                        username = operatorVO.getUserName();
//                    }
//                    break;
//                }
//            }
//        }//================
    }
    @Test
    public void test2(){

        String appenOpenid =  "http://XXXX?reg=abc 返回http://XXXX?reg=abc&openid=xxxxxxxxxx".contains("?")?"&openid=":"?openid=" ;
        System.out.println(appenOpenid);
    }

    @Autowired
    MenuService menuService ;
////    @Test
////    public void test3(){
////        Menu menu = new Menu() ;
////        menu.setParentId(0);
////        menu.setRemarker("xxx");
////        menu.setText("xxx管理");
////        menu.setUrl("www.baidu.com");
////        menuService.addMenu(menu) ;
////    }

    @Autowired
    TreeService treeService ;
    @Test
    public void  test4(){
        System.out.println(treeService.getTreeNodesByMenus(menuService.getAllMenu())) ;
        System.out.println(menuService.getAllMenu()) ;
    }
}
