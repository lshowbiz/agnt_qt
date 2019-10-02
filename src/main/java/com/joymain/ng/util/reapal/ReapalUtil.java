package com.joymain.ng.util.reapal;

import javax.jws.WebService;
/**
 * 融宝支付
 * @author Administrator
 *
 */
@WebService
public interface ReapalUtil {

	public Reapal getReapal(Reapal reapal, int flag);
}