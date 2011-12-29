/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

/**
 * Minecraft version enum.
 * @author kane
 */
public enum Version {
     Alpha1_2_6(2),
     Beta1_0(1),    Beta1_0_01(1), Beta1_0_02(2),
     Beta1_1(1),    Beta1_1_01(2), Beta1_1_02(2),
     Beta1_2(1),    Beta1_2_01(1), Beta1_2_02(2),
     Beta1_3(1),    Beta1_3_01(2),
     Beta1_4(1),    Beta1_4_01(2),
     Beta1_5(1),    Beta1_5_01(1), Beta1_5_02(2),
     Beta1_6(1),    Beta1_6_1(1),  Beta1_6_2(1),  Beta1_6_3(1),  Beta1_6_4(1),  Beta1_6_5(1),  Beta1_6_6(2),
     Beta1_7(1),    Beta1_7_01(1), Beta1_7_2(1),  Beta1_7_3(2), 
     Beta1_8p1(1),  Beta1_8p2(2),
     Beta1_8(1),    Beta1_8_1(2),
     Beta1_9p1(1),  Beta1_9p2(1),  Beta1_9p3(1),  Beta1_9p4(1),  Beta1_9p5(1),  Beta1_9p6(2),
     Main1_0_0(1),  Main1_0_1(0);
     public int outdatedFactor;
     // 0 = current
     // 1 = minor out-of-date
     // 2 = major out-of-date (sort of permissible)
     private Version(int i)
     {
         outdatedFactor = i;
     }
}
