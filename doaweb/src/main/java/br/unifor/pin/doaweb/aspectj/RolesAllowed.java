/**
 * 
 */
package br.unifor.pin.doaweb.aspectj;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.unifor.pin.doaweb.enums.TipoUsuario;

/**
 * @author patrick.cunha
 * @since 07/05/2015
 */
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.METHOD})
public @interface RolesAllowed {
	
	TipoUsuario[] value();

}
