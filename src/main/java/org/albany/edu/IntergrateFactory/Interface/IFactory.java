package org.albany.edu.IntergrateFactory.Interface;

import org.albany.edu.IntergrateFactory.FactoryConfiguration;
import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.DataHandler.IDatabase;
import org.albany.edu.IntergrateFactory.Interface.Imp.JsonParser;

/**
 * This interface define a factory that collect,restructure and store data into
 * database.
 * 
 * @author Yizhen Chen
 * @version 2015-8-17
 * 
 */
public interface IFactory {

	/**
	 * Retrieve data from data source
	 * <p>
	 * 
	 * @return Message
	 */
	Message collectionDate();

	/**
	 * Convert source text into JavaBean , return class definition.
	 * <p>
	 * 
	 * @param source
	 *            source text
	 * @param model
	 *            which class it map to
	 * @param className
	 * @param packageInfo
	 * 
	 * @return Message
	 */
	Message MapToModel(String source, String model, String className);

	JsonParser getParser();

	IReader getReader();

	FactoryConfiguration getConfig();

	IBuilder getBuilder();

	String initFactoryInDB();

	void CheckModel(boolean flag);

	Object getSamples(String jsonSource, Object t);

	Message getDataByModel();
	

}
