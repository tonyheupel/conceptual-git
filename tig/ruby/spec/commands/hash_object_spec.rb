require_relative '../spec_helper'

require 'commands/hash_object'

describe HashObject do

  # simulate using echo on the command line
  def echo(string)
    "#{string}\n"
  end

  describe "hash method" do
    it "should return the sha1 of a blob by default" do
      HashObject.hash(echo("foo")).must_equal '257cc5642cb1a054f08cc83f2d943e56fd3ebe99'
    end
  end
end


