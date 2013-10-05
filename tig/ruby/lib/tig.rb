#!/usr/bin/env ruby

require_relative './commands/hash_object'

def hash_object
  # ARGF creates stream from  filename input from command line or STDIN if no filename
  puts HashObject.hash(ARGF.read)
end

def get_command
  # not only gets the command
  # but also removes the command from the ARGF
  ARGV.shift
end

command = get_command
case command
  when 'hash-object' then hash_object()
  else puts "'#{command}' is an unknown command"
end

