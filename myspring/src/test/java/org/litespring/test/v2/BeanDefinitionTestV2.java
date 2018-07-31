package org.litespring.test.v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.impl.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

public class BeanDefinitionTestV2 {
	
	@Test
	public void testBeanDefinition() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		List<PropertyValue> pvs = bd.getPropertyValues();
		
		assertTrue(pvs.size() == 2);
		
		PropertyValue pv = this.getPropertyValues("accountDao", pvs);
		assertNotNull(pv);
		assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		
		PropertyValue pv2 = this.getPropertyValues("itemDao", pvs);
		assertNotNull(pv2);
		assertTrue(pv2.getValue() instanceof RuntimeBeanReference);
	}
	
	private PropertyValue getPropertyValues(String name, List<PropertyValue> pvList) {
		for(PropertyValue pv : pvList) {
			if(pv.getName().equals(name)) {
				return pv;
			}
		}
		return null;
	}
}
