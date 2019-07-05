# **Image Editing**



#### Project By: 
**Ahmed Mohamed Hassan** (Junior Software Developer)

[LinkedIn Profile](https://www.linkedin.com/in/ahmed-mohamed-128142105/ "LinkedIn Profile")

[Wuzzuf Profile](https://wuzzuf.net/me/Ahmed-Mohammed-34 "Wuzzuf Profile")


## Introduction
Image manipulation programs like Photoshop include various filters or transformations that are applied to images to produce different effects. In this report we are discussing a project that can apply four different transformations on a loaded image:
- Lighten Images
- Darken Images
- Blur Images
- Invert Images

## System Architecture and Design

[![System Architecture](http://i64.tinypic.com/2a6ngog.jpg "System Architecture")](http://i64.tinypic.com/2a6ngog.jpg "System Architecture")




## Structure of the code
[![Structure Of the Code](http://i66.tinypic.com/2cdzvd3.jpg "Structure Of the Code")](http://i66.tinypic.com/2cdzvd3.jpg "Structure Of the Code")
#### Modules Names:
- MainClass
- ImageClass
- ToolsClass
- FiltersClass

Once program runs, MainClass is fired and in this point we have only 2 options:
- Browse
- Exit
 If Exit then program terminates; if Browse then an open window appears to select the required image from computer then ImageClass is fired after selecting the image to reconstruct the second frame and call ToolsClass to draw the image.
**Now, we have 7 options:**
- Exit
- Back
- Save
- Undo
- Redo
- Apply Filter
- Crop Image
If Exit then program terminates, if Back then MainClass will be fired to select another image, if Save then a save window appears to save the image after editing, Undo is to reset the current editing of the image, Redo is to reset what Undo have been done, Apply Filter fires FiltersClass to apply the filter that have been chosen from the Filter Combo Box to the image then ToolsClass is fired again to redraw the image with the filter, Crop Image fires ToolsClass to crop the image after firing ToolClass to draw the shape that have been chosen from the Tool Combo Box with the coordinates specified by a mouse drag then ToolsClass if fired again to redraw the cropped image.¬¬

## User Interface


[![User Interface](http://i65.tinypic.com/2w6i6tx.jpg "User Interface")](http://i65.tinypic.com/2w6i6tx.jpg "User Interface")


[![User Interface](http://i64.tinypic.com/21c9hls.jpg "User Interface")](http://i64.tinypic.com/21c9hls.jpg "User Interface")


[![User Interface](http://i67.tinypic.com/1j4xag.jpg "User Interface")](http://i67.tinypic.com/1j4xag.jpg "User Interface")


[![User Interface](http://i64.tinypic.com/11j5etd.jpg "User Interface")](http://i64.tinypic.com/11j5etd.jpg "User Interface")

## Project Features

#### Load image
[![Load Image](http://i65.tinypic.com/5b4hmf.jpg "Load Image")](http://i65.tinypic.com/5b4hmf.jpg "Load Image")

#### Crop (Circle/Rectangle)
[![Rectangular Crop](http://i67.tinypic.com/kdan3o.jpg "Rectangular Crop")](http://i67.tinypic.com/kdan3o.jpg "Rectangular Crop")
[![Circular Crop](http://i64.tinypic.com/30j2qh3.jpg "Circular Crop")](http://i64.tinypic.com/30j2qh3.jpg "Circular Crop")
#### Image Filter
##### Light
[![Light Filter](http://i63.tinypic.com/v5bbsj.jpg "Light Filter")](http://i63.tinypic.com/v5bbsj.jpg "Light Filter")
##### Dark
[![Dark Filter](http://i63.tinypic.com/4q3kg2.jpg "Dark Filter")](http://i63.tinypic.com/4q3kg2.jpg "Dark Filter")
##### Blur
[![Blur Filter](http://i65.tinypic.com/16kw3tw.jpg "Blur Filter")](http://i65.tinypic.com/16kw3tw.jpg "Blur Filter")
##### Invert
[![Invert Filter](http://i66.tinypic.com/2qs70cz.jpg "Invert Filter")](http://i66.tinypic.com/2qs70cz.jpg "Invert Filter")

## How to run

Just run **ImageEditor/ImageEditor.exe**
