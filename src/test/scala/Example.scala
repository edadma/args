import java.io.File

import xyz.hyperreal.args.Options


object Example extends App {

	def usage: Unit = {
		// print usage
	}

	if (args isEmpty)
		usage

	Options( args ) {
		case "-d" :: name :: s :: t =>
			// define name = s
			t
		case "--help" :: _ =>
			usage
			Nil
		case "--" :: Nil =>
			// read from standard input (i.e. io.Source.stdin.mkString)
			Nil
		case s :: _ if s startsWith "-" => sys.error( s"invalid switch $s" )
		case file :: Nil =>
			val input = new File( file )

			if (input.exists && input.isFile && input.canRead) {
				// read from file (i.e. io.Source.fromFile(input).mkString)
				Nil
			} else
				sys.error( s"error reading file: $file" )
	}

}