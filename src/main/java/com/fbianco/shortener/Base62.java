package com.fbianco.shortener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Base62{

    private static final char[] ALPHABET = new char[ 62 ];
    private static final Map<Character, Integer> ALPHABET_MAPPING = new HashMap<>();
    private static final BigInteger ENCODE_LENGTH = BigInteger.valueOf( ALPHABET.length );

    static{
        int position = 0;
        // numbers
        for( int i = 48; i <= 57; i++ ){
            ALPHABET[ position++ ] = (char)i;
        }
        // uppercase letters
        for( int i = 65; i <= 90; i++ ){
            ALPHABET[ position++ ] = (char)i;
        }
        // lowercase letters
        for( int i = 97; i <= 122; i++ ){
            ALPHABET[ position++ ] = (char)i;
        }
        for( int i = 0; i < ALPHABET.length; i++ ){
            ALPHABET_MAPPING.put( ALPHABET[ i ], i );
        }
    }

    public static String encode( final BigInteger in ){
        final List<Character> list = new ArrayList<>();
        boolean negative = in.signum() == -1;
        BigInteger use;
        if( negative ){
            use = in.negate();
        } else {
            use = in;
        }

        do{
            BigInteger[] divisionResultAndReminder = use.divideAndRemainder( ENCODE_LENGTH );
            list.add( ALPHABET[ divisionResultAndReminder[ 1 ].intValue() ] );
            use = divisionResultAndReminder[ 0 ];
        } while( use.equals( BigInteger.ZERO ) == false );

        Collections.reverse( list );

        char[] res = new char[ list.size() ];
        for( int i = 0; i < list.size(); i++ ){
            res[ i ] = list.get( i );
        }

        return ( negative ? "-" : "" ) + new String( res );
    }

    public static BigInteger decode( final String encoded ){
        BigInteger res = BigInteger.ZERO;
        char c;
        boolean negative;
        String use;
        if( '-' == encoded.charAt( 0 ) ){
            negative = true;
            use = encoded.substring( 1 );
        } else {
            negative = false;
            use = encoded;
        }

        for( int index = 0; index < use.length(); index++ ){
            c = use.charAt( index );
            res = res.multiply( ENCODE_LENGTH );
            res = res.add( BigInteger.valueOf( ALPHABET_MAPPING.get( c ) ) );
        }
        return negative ? res.negate() : res;
    }
}