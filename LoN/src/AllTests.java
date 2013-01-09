
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import GameField.FieldTest;
import GameField.GameFieldTest;
import MathematicalLogik.MultDivCheck;
import MathematicalLogik.MultDivCheckTest;
import MathematicalLogik.PlusMinusCheck;
import MathematicalLogik.PlusMinusCheckTest;

/**
 * 
 */

/**
 * @author rana
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ FieldTest.class, GameFieldTest.class, PlusMinusCheckTest.class, MultDivCheckTest.class })
public class AllTests {

}
