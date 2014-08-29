/*
 * Copyright 2014 Massachusetts General Hospital
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding

import org.codehaus.groovy.grails.web.json.JSONObject

import com.hp.hpl.jena.rdf.model.Literal
import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.ResourceFactory


/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class AnnotatorJsEncoder implements IOpenAnnotationJsonEncoder {

	
	private void addStatement(Model model, Resource subject, String predicate, String object) {
		model.add(subject, ResourceFactory.createProperty(predicate), ResourceFactory.createResource(object));
	}
	
	private void addStatement(Model model, Resource subject, String predicate, Resource object) {
		model.add(subject, ResourceFactory.createProperty(predicate), object);
	}
	
	private void addStatement(Model model, Resource subject, String predicate,  Literal object) {
		model.add(subject, ResourceFactory.createProperty(predicate), object);
	}
	
	private void createTextualBody(Model model, Resource annotationUri,  Resource bodyUri, String text) {
		addStatement(model, bodyUri, IRdfVocabulary.PROPERTY_TYPE_URI, IDublinCoreTypesVocabulary.CLASS_TEXT_URI);
		addStatement(model, bodyUri, IRdfVocabulary.PROPERTY_TYPE_URI, IContentAsRdfVocabulary.CLASS_CONTENTASTEXT_URI);
		addStatement(model, bodyUri, (IDublinCoreElementsVocabulary.PROPERTY_FORMAT_URI), ResourceFactory.createPlainLiteral(IMimeTypesVocabulary.VALUE_TEXT_JSON));
		addStatement(model, bodyUri, IContentAsRdfVocabulary.PROPERTY_CHARS_URI, ResourceFactory.createPlainLiteral(text));
		addStatement(model, annotationUri, IOpenAnnotation.PROPERTY_HASBODY_URI, bodyUri);
	}
	
	@Override
	public Model encode(JSONObject json) {
		// Graph URI
		//org.openrdf.model.URI context1 = f.createURI("http://example.org/annotation/graph/" + System.currentTimeMillis()); // Change to UUID
		
		//org.openrdf.model.URI annotationUri = f.createURI("http://example.org/annotation/" + json.id);
		//json.put("uri", annotationUri.toString());
		Resource annotationUri = ResourceFactory.createResource("http://example.org/annotation/" + json.id);
		try {
			Model model = ModelFactory.createDefaultModel();
				// Annotation type
				addStatement(model, annotationUri, IRdfVocabulary.PROPERTY_TYPE_URI, IOpenAnnotation.CLASS_ANNOTATION_URI);
				
				// Annotation body
				Resource bodyUri = ResourceFactory.createResource("http://example.org/body/" + json.id);
				createTextualBody(model, annotationUri, bodyUri, json.text);
				
				// Annotation target
				Resource targetUri = ResourceFactory.createResource("http://example.org/target/" + json.id);
				addStatement(model, annotationUri, IOpenAnnotation.PROPERTY_HASTARGET_URI, targetUri);
				addStatement(model, targetUri, IRdfVocabulary.PROPERTY_TYPE_URI, IOpenAnnotation.CLASS_SPECIFICRESOURCE_URI);
				addStatement(model, targetUri, IOpenAnnotation.PROPERTY_HASSOURCE_URI, json.uri);
				
				// Annotation target selector
				for(int i=0; i<json.ranges.size(); i++) {
					Resource selectorUri = ResourceFactory.createResource("http://example.org/selector/" + json.id + "_" + i);
					addStatement(model, targetUri, IOpenAnnotation.PROPERTY_HASSELECTOR_URI, selectorUri);
					addStatement(model, selectorUri, IRdfVocabulary.PROPERTY_TYPE_URI, "http://www.annotationframework.org/ns/af#TextPositionSelector");
					addStatement(model, selectorUri, "http://www.annotationframework.org/ns/af#start", ResourceFactory.createPlainLiteral(json.ranges[i].start));
					addStatement(model, selectorUri, "http://www.annotationframework.org/ns/af#startOffset", ResourceFactory.createPlainLiteral(Integer.toString(json.ranges[i].startOffset)));
					addStatement(model, selectorUri, "http://www.annotationframework.org/ns/af#end", ResourceFactory.createPlainLiteral(json.ranges[i].end));
					addStatement(model, selectorUri, "http://www.annotationframework.org/ns/af#endOffset", ResourceFactory.createPlainLiteral(Integer.toString(json.ranges[i].endOffset)));
				}						
				return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
