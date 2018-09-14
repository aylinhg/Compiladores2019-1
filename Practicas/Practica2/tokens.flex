// tokens.flex
%%
%class letras
%public
%unicode

%%
a {return A;}
b {return B;}
[^] {System.out.println("ERROR: Token no reconocido.");}
