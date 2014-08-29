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
package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding;

/**
 * List of classes and properties of the Open Annotation Model.
 * Website: http://www.openannotation.org/spec/core/
 * 
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
public interface IOpenAnnotation {

	public static final String NAMESPACE = "http://www.w3.org/ns/oa#";
	
	public static final String CLASS_ANNOTATION = "oa:Annotation";
	public static final String CLASS_ANNOTATION_NAME = "Annotation";
	public static final String CLASS_ANNOTATION_URI = NAMESPACE + "Annotation";
	public static final String CLASS_SPECIFICRESOURCE_URI = NAMESPACE + "SpecificResource";
	public static final String CLASS_TEXTPOSITIONSELECTOR_URI = NAMESPACE + "TextPositionSelector";
	
	public static final String PROPERTY_HASBODY_URI = NAMESPACE + "hasBody";
	public static final String PROPERTY_HASTARGET_URI = NAMESPACE + "hasTarget";
	public static final String PROPERTY_HASSOURCE_URI = NAMESPACE + "hasSource";
	public static final String PROPERTY_HASSELECTOR_URI = NAMESPACE + "hasSelector";
	
	public static final String PROPERTY_START_URI = NAMESPACE + "start";
	public static final String PROPERTY_END_URI = NAMESPACE + "end";
	
}
