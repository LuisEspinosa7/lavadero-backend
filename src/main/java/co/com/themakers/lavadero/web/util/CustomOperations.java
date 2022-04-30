/**
 * 
 */
package co.com.themakers.lavadero.web.util;

import java.math.BigDecimal;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class CustomOperations {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
		return base.multiply(pct).divide(ONE_HUNDRED);
	}

}
