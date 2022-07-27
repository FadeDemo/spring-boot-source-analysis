package org.fade.demo.springframework.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态Controller
 *
 * @author fade
 * @date 2022/07/27
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicController {

	@Value("${dynamic}")
	private String dynamic;

	@GetMapping("/getDynamic")
	public String getDynamic() {
		return this.dynamic;
	}

}
