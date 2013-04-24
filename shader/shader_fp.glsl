uniform sampler3D vol1;
uniform sampler2D lut1;

uniform sampler3D vol2;
uniform sampler2D lut2;

uniform float iso;
uniform vec4 color;

uniform vec3 scale;
uniform vec3 translate;

void main()
{
    vec4 data1 = texture3D(vol1, gl_TexCoord[0].xyz);
    vec4 col1 = texture2D(lut1, data1.xx);
	
	vec3 roi_coord = (gl_TexCoord[0].xyz - translate) / scale;
    vec4 data2 = texture3D(vol2, roi_coord);
    vec4 col2 = texture2D(lut2, data2.xx);
	
//    gl_FragColor = col1 + (1.0 - col1.a) * col2;

    float f2 =  smoothstep(0.15, 0.0, abs(iso - data2.x));
    col2 = color * f2;
    col2.rgb *= f2;
    gl_FragColor = col1 + (1.0 - col1.a) * col2;

}
