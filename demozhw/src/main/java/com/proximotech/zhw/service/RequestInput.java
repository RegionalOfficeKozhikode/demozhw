/**
 * 
 */
package com.proximotech.zhw.service;

import org.springframework.stereotype.Component;

import com.proximotech.input.Input;

/**
 * @author Apple
 *
 *
 */
@Component
public class RequestInput {
	
	public  Input input;
	
	public void setInput(Input input){
		this.input = input;
	}
	
	public Input getInput() {
		return input;
	}

}
