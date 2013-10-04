#!/usr/bin/env ruby

require './lib/commands/hash_object'

def hash_object
  puts sha1(ARGF.read)
end

def get_op
  ARGV.shift
end

case get_op
  when 'hash-object' then hash_object()
  else puts "unknown command"
end

