Welcome to the AVT Image Annotation&trade; Project!
===================================================

The Algorithm Validation Toolkit&trade; (AVT&trade;) Open Source project is a set
of tools that facilitate the testing and statistical comparison of image processing
algorithms.  It is built upon the
[eXtensible Imaging Platform&trade; (XIP&trade;)](http://www.OpenXIP.org) development
framework, and can create and consume objects based
on [AIM (Annotation and Image Markup] (https://github.com/NCIP/annotation-and-image-markup)
models.  

The Algorithm Execution&trade; (IA&trade;) component of AVT&trade; is an XIP-based 
[DICOM Hosted Application](http://medical.nema.org/Dicom/2011/11_19pu.pdf)
for creating AIM objects associated with DICOM studies.  IA can be run using a
[DICOM Hosting System](http://medical.nema.org/Dicom/2011/11_19pu.pdf),
such as the [XIP Host&trade;](https://github.com/OpenXIP/xip-host).
AE&trade; is implemented in Java with Swing, and utilizes scene graphs
created with the [XIP Libraries&trade;](https://github.com/OpenXIP/xip-libraries).

The ultimate goals of the project include:

* Serve as a more complete example of an XIP-based DICOM Hosted Application.
* Provide mechanisms to view a volumetric DICOM dataset.
* Provide a mechanism to create RECIST and/or WHO annotations on that dataset.
* Provide semi-automatic segmentation of lesions such as lung nodules
  + with manual editing tools to improve the segmentation,
  + storing the results as DICOM segmentation objects referenced by AIM objects
    that include volumetric measurements of the lesion.

AVT&trade;, including AVT IA&trade; is distributed under the
[Apache 2.0 License](http://opensource.org/licenses/Apache-2.0).
Please see the NOTICE and LICENSE files for details.

You will find more details about AVT&trade; and XIP&trade; in the following links:

*  [Home Page] (http://www.OpenXIP.org)
*  [Forum/Mailing List] (https://groups.google.com/forum/?fromgroups#!forum/openxip)
*  [Issue tracker] (https://plans.imphub.org/browse/XIP)
*  [Documentation] (https://docs.imphub.org/display/XIP)
*  [Git code repository] (https://github.com/OpenXIP/xip-host)
