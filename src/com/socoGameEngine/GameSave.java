package com.socoGameEngine;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface GameSave {
	/**
	 * 
	 * @param p ..
	 */
	void writefile(ObjectOutputStream p)throws Exception;
	void loadfile(ObjectInputStream p)throws Exception;
}
